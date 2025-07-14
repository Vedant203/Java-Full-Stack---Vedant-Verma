import React, { useState, useEffect } from 'react';
import { useParams, useLocation, useNavigate } from 'react-router-dom';
import quizService from '../services/quizService';

function PreSubmissionReview() {
    const { id } = useParams();
    const location = useLocation();
    const navigate = useNavigate();
    
    // The user's selected answers are passed via the 'state' property in the navigate function
    const { answers } = location.state || {};
    
    const [quiz, setQuiz] = useState(null);

    useEffect(() => {
        // We still need to fetch the quiz to get the question texts
        quizService.getQuiz(id).then(response => setQuiz(response.data));
    }, [id]);

    const handleEditAnswer = (questionIndex) => {
        // Navigate back to the quiz page, passing the current answers and the index of the question to jump to
        navigate(`/quiz/${id}`, { state: { answers: answers, jumpToQuestionIndex: questionIndex } });
    };

    const handleFinalSubmit = () => {
        const formattedAnswers = Object.keys(answers).map(questionText => ({
            questionText: questionText,
            answer: answers[questionText]
        }));
        
        quizService.submitQuiz(id, formattedAnswers)
            .then(res => navigate(`/quiz-complete/${res.data.resultId}`));
    };

    if (!quiz) return <p>Loading review...</p>;

    return (
        <div className="review-page">
            <h1 style={{textAlign: 'center'}}>Review Your Answers</h1>
            <p style={{textAlign: 'center', color: 'var(--text-secondary)'}}>
                Please review your selections below. You can edit any answer before your final submission.
            </p>
            <div className="review-list" style={{display: 'flex', flexDirection: 'column', gap: '1rem', marginTop: '2rem'}}>
                {quiz.questions.map((question, index) => (
                    <div key={index} className="card review-card--presubmit">
                        <p><strong>Question {index + 1}:</strong> {question.questionText}</p>
                        <div className="answer-line">
                           Your selected answer: <strong>{answers[question.questionText] || 'Not Answered'}</strong>
                        </div>
                        <div className="review-card__actions">
                            <button onClick={() => handleEditAnswer(index)} className="btn btn--secondary">
                                Edit
                            </button>
                        </div>
                    </div>
                ))}
            </div>
            <div className="button-group" style={{display: 'flex', gap: '1rem', justifyContent: 'center', marginTop: '2rem', borderTop: '1px solid var(--border-color)', paddingTop: '2rem'}}>
                 <button onClick={handleFinalSubmit} className="btn btn--success">
                    Submit Final Answers
                 </button>
            </div>
        </div>
    );
}

export default PreSubmissionReview;