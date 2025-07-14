import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, useLocation } from 'react-router-dom';
import quizService from '../services/quizService';

function Quiz() {
    const { id } = useParams();
    const navigate = useNavigate();
    const location = useLocation();

    const [quiz, setQuiz] = useState(null);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    
    // Initialize state from location if it exists, otherwise start with an empty object
    // This preserves answers when navigating back from the review page
    const [selectedAnswers, setSelectedAnswers] = useState(location.state?.answers || {});

    useEffect(() => {
        quizService.getQuiz(id).then(response => {
            setQuiz(response.data);
            // If we are navigating back from the review page to edit, jump to the correct question
            if (location.state?.jumpToQuestionIndex !== undefined) {
                setCurrentQuestionIndex(location.state.jumpToQuestionIndex);
            }
        });
    }, [id, location.state]);

    const handleOptionSelect = (qText, option) => {
        setSelectedAnswers(prev => ({ ...prev, [qText]: option }));
    };
    
    const handleClearSelection = (qText) => {
        const newAnswers = { ...selectedAnswers };
        delete newAnswers[qText];
        setSelectedAnswers(newAnswers);
    };

    const handleNext = () => {
        if (currentQuestionIndex < quiz.questions.length - 1) {
            setCurrentQuestionIndex(prev => prev + 1);
        }
    };

    const handleReview = () => {
        // Navigate to the review page and pass the current answers in the state
        navigate(`/review-submission/${id}`, { state: { answers: selectedAnswers } });
    };

    const handleSubmit = () => {
        const formattedAnswers = Object.keys(selectedAnswers).map(qText => ({
            questionText: qText,
            answer: selectedAnswers[qText]
        }));
        quizService.submitQuiz(id, formattedAnswers)
            .then(res => navigate(`/quiz-complete/${res.data.resultId}`));
    };

    if (!quiz) return <p>Loading...</p>;

    const currentQuestion = quiz.questions[currentQuestionIndex];
    const isSelected = selectedAnswers[currentQuestion.questionText] !== undefined;
    const isLastQuestion = currentQuestionIndex === quiz.questions.length - 1;

    return (
        <div className="card quiz-page">
            <div className="quiz-header">
                <h1>{quiz.title}</h1>
                <div className="progress-bar-container">
                    <div className="progress-bar" style={{ width: `${((currentQuestionIndex + 1) / quiz.questions.length) * 100}%` }}></div>
                </div>
            </div>
            <p className="question-text">{currentQuestion.questionText}</p>
            <div className="options-list">
                {currentQuestion.options.map((option, index) => (
                    <button key={index}
                        className={`option-btn ${selectedAnswers[currentQuestion.questionText] === option ? 'selected' : ''}`}
                        onClick={() => handleOptionSelect(currentQuestion.questionText, option)}>
                        {option}
                    </button>
                ))}
            </div>
            <div className="quiz-navigation">
                {isSelected && <button onClick={() => handleClearSelection(currentQuestion.questionText)} className="btn btn--secondary">Clear</button>}
                
                <div style={{marginLeft: 'auto', display: 'flex', gap: '1rem'}}>
                    {isLastQuestion ? (
                        <>
                            <button onClick={handleReview} className="btn btn--secondary">Review Answers</button>
                            <button onClick={handleSubmit} className="btn btn--success">Submit</button>
                        </>
                    ) : (
                        <button onClick={handleNext} className="btn btn--primary">Next</button>
                    )}
                </div>
            </div>
        </div>
    );
}

export default Quiz;