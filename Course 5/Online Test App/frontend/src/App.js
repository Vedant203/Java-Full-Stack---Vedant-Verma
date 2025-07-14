import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import QuizList from './components/QuizList';
import Quiz from './components/Quiz';
import QuizComplete from './components/QuizComplete';
import DisplayResult from './components/DisplayResult';
import Review from './components/Review';
import PreSubmissionReview from './components/PreSubmissionReview';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app-container">
        <header className="app-header">
          <nav className="navbar">
            <Link to="/" className="navbar__brand">QuizMaster</Link>
            <div className="navbar__links">
              <Link to="/">Home</Link>
            </div>
          </nav>
        </header>
        <main>
          <Routes>
            <Route path="/" element={<QuizList />} />
            <Route path="/quiz/:id" element={<Quiz />} />
            <Route path="/quiz-complete/:resultId" element={<QuizComplete />} />
            <Route path="/result/:id" element={<DisplayResult />} />
            <Route path="/review/:id" element={<Review />} />
            <Route path="/review-submission/:id" element={<PreSubmissionReview />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;