import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import quizService from '../services/quizService';

function QuizList() {
    const [quizzes, setQuizzes] = useState([]);
    useEffect(() => {
        quizService.getQuizzes().then(response => setQuizzes(response.data));
    }, []);

    return (
        <div className="quiz-list">
            <h1>Online Quizzes</h1>
            <div className="quiz-grid">
                {quizzes.map(quiz => (
                    <div key={quiz._id} className="card quiz-card">
                        <h2>{quiz.title}</h2>
                        <p>{quiz.description}</p>
                        <Link to={`/quiz/${quiz._id}`} className="btn btn--primary">
                            Start Quiz
                        </Link>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default QuizList;