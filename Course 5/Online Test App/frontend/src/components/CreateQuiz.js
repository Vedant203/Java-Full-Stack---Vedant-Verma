import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import quizService from '../services/quizService';

function CreateQuiz() {
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [questions, setQuestions] = useState([{ questionText: '', options: ['', ''], correctAnswer: '' }]);

    const handleQuestionChange = (index, event) => {
        const newQuestions = questions.map((q, i) => {
            if (i === index) {
                return { ...q, [event.target.name]: event.target.value };
            }
            return q;
        });
        setQuestions(newQuestions);
    };

    const handleOptionChange = (qIndex, oIndex, event) => {
        const newQuestions = questions.map((q, i) => {
            if (i === qIndex) {
                const newOptions = q.options.map((o, j) => (j === oIndex ? event.target.value : o));
                return { ...q, options: newOptions };
            }
            return q;
        });
        setQuestions(newQuestions);
    };

    const addQuestion = () => {
        setQuestions([...questions, { questionText: '', options: ['', ''], correctAnswer: '' }]);
    };

    const removeQuestion = (index) => {
        const newQuestions = questions.filter((_, i) => i !== index);
        setQuestions(newQuestions);
    };
    
    const handleSubmit = (event) => {
        event.preventDefault();
        const quizData = { title, description, questions };
        quizService.createQuiz(quizData)
            .then(() => {
                alert('Quiz created successfully!');
                navigate('/');
            })
            .catch(error => {
                console.error("Error creating quiz:", error);
                alert('Failed to create quiz.');
            });
    };

    return (
        <div className="card-container create-quiz-container">
            <h1>Create a New Quiz</h1>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Quiz Title</label>
                    <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label>Quiz Description</label>
                    <textarea value={description} onChange={(e) => setDescription(e.target.value)} />
                </div>

                {questions.map((q, qIndex) => (
                    <div key={qIndex} className="question-form-group">
                        <h3>Question {qIndex + 1}</h3>
                        <div className="form-group">
                            <label>Question Text</label>
                            <input name="questionText" value={q.questionText} onChange={(e) => handleQuestionChange(qIndex, e)} required />
                        </div>
                        {q.options.map((opt, oIndex) => (
                            <div className="form-group" key={oIndex}>
                                <label>Option {oIndex + 1}</label>
                                <input value={opt} onChange={(e) => handleOptionChange(qIndex, oIndex, e)} required />
                            </div>
                        ))}
                        <div className="form-group">
                            <label>Correct Answer</label>
                            <select name="correctAnswer" value={q.correctAnswer} onChange={(e) => handleQuestionChange(qIndex, e)} required>
                                <option value="" disabled>Select the correct answer</option>
                                {q.options.map((opt, oIndex) => opt && <option key={oIndex} value={opt}>{opt}</option>)}
                            </select>
                        </div>
                        <button type="button" onClick={() => removeQuestion(qIndex)} className="btn btn-danger">Remove Question</button>
                    </div>
                ))}
                
                <button type="button" onClick={addQuestion} className="btn btn-secondary">Add Another Question</button>
                <button type="submit" className="btn btn-primary btn-submit">Save Quiz</button>
            </form>
        </div>
    );
}

export default CreateQuiz;