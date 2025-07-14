import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import quizService from '../services/quizService';

function Result() {
    const { id } = useParams();
    const [result, setResult] = useState(null);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        quizService.getResult(id).then(response => {
            setResult(response.data);
            setIsLoading(false);
        }).catch(error => console.error("Error fetching result:", error));
    }, [id]);

    if (isLoading) return <p>Loading results...</p>;
    if (!result) return <p>Result not found!</p>;

    const percentage = ((result.score / result.totalQuestions) * 100).toFixed(2);

    return (
        <div className="result-container">
            <h1>Result for: {result.quizId.title}</h1>
            <div className="score-summary">
                <h2>You Scored: {result.score} out of {result.totalQuestions} ({percentage}%)</h2>
            </div>
            <div className="review-section">
                <h2>Review Your Answers</h2>
                {result.submittedAnswers.map((item, index) => (
                    <div key={index} className={`review-item ${item.isCorrect ? 'correct' : 'incorrect'}`}>
                        <p className="question-text"><strong>Q: {item.questionText}</strong></p>
                        <p>Your Answer: {item.submittedAnswer}</p>
                        {!item.isCorrect && <p>Correct Answer: {item.correctAnswer}</p>}
                    </div>
                ))}
            </div>
            <Link to="/" className="home-btn">Back to Quizzes</Link>
        </div>
    );
}

export default Result;