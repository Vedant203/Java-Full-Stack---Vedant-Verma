import React from 'react';
import { Link, useParams } from 'react-router-dom';

function QuizComplete() {
    const { resultId } = useParams();
    return (
        <div className="card confirmation-page">
            <h1>Quiz Submitted!</h1>
            <p>Quiz completed. Check your score or review your answers.</p>
            <div className="button-group" style={{display: 'flex', gap: '1rem', justifyContent: 'center', marginTop: '2rem'}}>
                <Link to={`/result/${resultId}`} className="btn btn--primary">Show Score</Link>
                <Link to={`/review/${resultId}`} className="btn btn--secondary">Review Answers</Link>
            </div>
        </div>
    );
}
export default QuizComplete;