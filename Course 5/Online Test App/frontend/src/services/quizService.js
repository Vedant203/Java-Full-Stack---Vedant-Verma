import axios from 'axios';

const API_URL = 'http://localhost:5000/api';

const getQuizzes = () => {
    return axios.get(`${API_URL}/quizzes`);
};
const getQuiz = (id) => {
    return axios.get(`${API_URL}/quizzes/${id}`);
};
const submitQuiz = (id, answers) => {
    return axios.post(`${API_URL}/quizzes/${id}/submit`, { answers });
};
const getResult = (id) => {
    return axios.get(`${API_URL}/results/${id}`);
};
const quizService = {
    getQuizzes,
    getQuiz,
    submitQuiz,
    getResult
};
export default quizService;