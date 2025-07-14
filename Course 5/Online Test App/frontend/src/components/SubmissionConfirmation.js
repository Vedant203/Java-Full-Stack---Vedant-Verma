import React from 'react';
import { Link, useParams } from 'react-router-dom';

function SubmissionConfirmation() {
    const { resultId } = useParams(); // Get the result ID from the URL

    return (
        <div className="submission-container">
            <h2>Quiz Submitted Successfully!</h2>
            <p>You have completed the quiz. What would you like to do next?</p>
            <div className="options-buttons">
                <Link to={`/result/${resultId}`} className="action-btn review-btn">
                    Review & Display Result
                </Link>
                <Link to="/" className="action-btn home-btn-alt">
                    Back to Quiz List
                </Link>
            </div>
        </div>
    );
}

export default SubmissionConfirmation;