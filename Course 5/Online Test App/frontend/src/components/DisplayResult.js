import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import quizService from '../services/quizService';

function DisplayResult() {
    const { id } = useParams();
    const [result, setResult] = useState(null);
    useEffect(() => {
        quizService.getResult(id).then(response => setResult(response.data));
    }, [id]);

    if (!result) return <p>Loading score...</p>;
    
    const percentage = Math.round((result.score / result.totalQuestions) * 100);

    return (
        <div className="card result-page">
            <h1>Your Score</h1>
            <div className="progress-circle" style={{'--p': percentage}}>
                <div className="progress-text">{percentage}%</div>
            </div>
            <p>You answered <strong>{result.score}</strong> out of <strong>{result.totalQuestions}</strong> questions correctly.</p>
            <div className="button-group" style={{display: 'flex', gap: '1rem', justifyContent: 'center', marginTop: '2rem'}}>
                <Link to={`/review/${id}`} className="btn btn--secondary">Review Answers</Link>
                <Link to="/" className="btn btn--primary">Take Another Quiz</Link>
            </div>
        </div>
    );
}
export default DisplayResult;