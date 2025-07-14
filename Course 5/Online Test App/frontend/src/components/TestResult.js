import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import quizService from '../services/quizService';

function TestResult() {
    const { id } = useParams();
    const [result, setResult] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        quizService.getResult(id)
            .then(response => {
                setResult(response.data);
                setIsLoading(false);
            })
            .catch(error => {
                console.error("Error fetching result:", error);
                setIsLoading(false);
            });
    }, [id]);

    if (isLoading) {
        return <div className="loading">Loading Results...</div>;
    }

    if (!result) {
        return <div className="error">Could not load results.</div>;
    }

    const score = result.score;
    const total = result.totalQuestions;
    const percentage = Math.round((score / total) * 100);

    const getFeedback = () => {
        if (percentage === 100) return "Perfect Score! You're a genius!";
        if (percentage >= 80) return "Excellent Work!";
        if (percentage >= 60) return "Good Job! Keep it up!";
        if (percentage >= 40) return "Not bad! Keep practicing.";
        return "Don't give up! Try again.";
    };

    return (
        <div className="test-result-container">
            {/* --- DISPLAY RESULT SECTION --- */}
            <div className="card-container result-display">
                <h1>Your Result</h1>
                <p className="result-feedback">{getFeedback()}</p>
                <div className="progress-circle" style={{ '--p': percentage }}>
                    <div className="progress-text">{percentage}%</div>
                </div>
                <p className="score-text">You scored <strong>{score}</strong> out of <strong>{total}</strong> questions.</p>
            </div>

            {/* --- REVIEW SECTION --- */}
            <div className="review-container">
                <h2>Detailed Review</h2>
                <div className="review-list">
                    {result.submittedAnswers.map((item, index) => (
                        <div key={index} className="card-container review-item">
                            <p className="question-text"><strong>{index + 1}. {item.questionText}</strong></p>
                            <div className={`answer-line ${item.isCorrect ? 'correct' : 'incorrect'}`}>
                               Your Answer: {item.submittedAnswer}
                            </div>
                            {!item.isCorrect && (
                                <div className="answer-line correct-answer">
                                    Correct Answer: {item.correctAnswer}
                                </div>
                            )}
                        </div>
                    ))}
                </div>
            </div>

             <div className="button-group">
                 <Link to="/" className="btn btn-primary">Back to Quizzes</Link>
            </div>
        </div>
    );
}

export default TestResult;