import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import quizService from '../services/quizService';
function Review() {
const { id } = useParams();
const [result, setResult] = useState(null);
useEffect(() => {
quizService.getResult(id).then(response => setResult(response.data));
}, [id]);

    if (!result) return <p>Loading review...</p>;

return (
    <div className="review-page">
        <h1 style={{textAlign: 'center'}}>Review Quiz</h1>
        <div className="review-list" style={{display: 'flex', flexDirection: 'column', gap: '1rem'}}>
            {result.submittedAnswers.map((item, index) => (
                <div key={index} className="card">
                    <p><strong>Question {index + 1}:</strong> {item.questionText}</p>
                    <div className={`answer-line ${item.isCorrect ? 'answer-line--correct' : 'answer-line--incorrect'}`}>
                       Your answer: {item.submittedAnswer}
                    </div>
                    {!item.isCorrect && (
                        <div className="answer-line correct-answer-text">
                            Correct answer: {item.correctAnswer}
                        </div>
                    )}
                </div>
            ))}
        </div>
         <div className="button-group" style={{display: 'flex', gap: '1rem', justifyContent: 'center', marginTop: '2rem'}}>
             <Link to="/" className="btn btn--primary">Back to Quizzes</Link>
        </div>
    </div>
);
}
export default Review;