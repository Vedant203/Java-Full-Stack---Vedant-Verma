const express = require('express');
const router = express.Router();
const Quiz = require('../models/quiz.model');
const Result = require('../models/result.model');

// router.post('/seed', async (req, res) => {
//     try {
//         await Quiz.deleteMany({});
//         const sampleQuizzes = [
//             // --- QUIZ 1: Indian History ---
// {
//   "title": "Indian History Quiz",
//   "description": "Test your knowledge of India's rich historical past.",
//   "questions": [
//     {
//       "questionText": "Who was the first Emperor of the Maurya Dynasty?",
//       "options": ["Ashoka", "Chandragupta Maurya", "Bindusara", "Harshavardhana"],
//       "correctAnswer": "Chandragupta Maurya"
//     },
//     {
//       "questionText": "Which Mughal emperor built the Taj Mahal?",
//       "options": ["Akbar", "Babur", "Shah Jahan", "Aurangzeb"],
//       "correctAnswer": "Shah Jahan"
//     },
//     {
//       "questionText": "When did India gain independence from British rule?",
//       "options": ["1945", "1947", "1950", "1930"],
//       "correctAnswer": "1947"
//     },
//     {
//       "questionText": "Who was the founder of the Sikh religion?",
//       "options": ["Guru Arjan Dev", "Guru Gobind Singh", "Guru Nanak", "Guru Tegh Bahadur"],
//       "correctAnswer": "Guru Nanak"
//     },
//     {
//       "questionText": "Which battle marked the beginning of British rule in India?",
//       "options": ["Battle of Plassey", "Battle of Panipat", "Battle of Haldighati", "Battle of Buxar"],
//       "correctAnswer": "Battle of Plassey"
//     }
//   ]
// }
// ,
//             {
//   "title": "India Capital Challenge",
//   "description": "How well do you know the capitals of Indian states?",
//   "questions": [
//     {
//       "questionText": "What is the capital of Uttar Pradesh?",
//       "options": ["Lucknow", "Kanpur", "Varanasi", "Agra"],
//       "correctAnswer": "Lucknow"
//     },
//     {
//       "questionText": "What is the capital of Tamil Nadu?",
//       "options": ["Chennai", "Madurai", "Coimbatore", "Tiruchirappalli"],
//       "correctAnswer": "Chennai"
//     },
//     {
//       "questionText": "What is the capital of Maharashtra?",
//       "options": ["Nagpur", "Pune", "Mumbai", "Nashik"],
//       "correctAnswer": "Mumbai"
//     },
//     {
//       "questionText": "What is the capital of West Bengal?",
//       "options": ["Kolkata", "Howrah", "Asansol", "Siliguri"],
//       "correctAnswer": "Kolkata"
//     },
//     {
//       "questionText": "What is the capital of Rajasthan?",
//       "options": ["Jaipur", "Udaipur", "Jodhpur", "Kota"],
//       "correctAnswer": "Jaipur"
//     }
//   ]
// }

//         ];
//         await sampleQuiz.save();
//         res.status(201).json({ message: "Sample quiz seeded successfully!" });
//     } catch (error) {
//         res.status(500).json({ message: "Error seeding data", error: error.message });
//     }
// });


// GET all available quizzes (only title and description)
router.get('/quizzes', async (req, res) => {
    try {
        const quizzes = await Quiz.find({}, '_id title description');
        res.json(quizzes);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// GET a single quiz by ID (without answers)
router.get('/quizzes/:id', async (req, res) => {
    try {
        // Select all fields except the correctAnswer within the questions array
        const quiz = await Quiz.findById(req.params.id).select('-questions.correctAnswer');
        if (!quiz) return res.status(404).json({ message: 'Quiz not found' });
        res.json(quiz);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// POST to submit a quiz and get results
router.post('/quizzes/:id/submit', async (req, res) => {
    try {
        const quiz = await Quiz.findById(req.params.id);
        if (!quiz) return res.status(404).json({ message: 'Quiz not found' });

        const submittedAnswers = req.body.answers; // Expects an array like [{ questionText: '...', answer: '...' }]
        let score = 0;
        const resultDetails = [];

        quiz.questions.forEach((question, index) => {
            const submitted = submittedAnswers.find(a => a.questionText === question.questionText);
            const isCorrect = submitted && submitted.answer === question.correctAnswer;
            if (isCorrect) {
                score++;
            }
            resultDetails.push({
                questionText: question.questionText,
                submittedAnswer: submitted ? submitted.answer : "No answer",
                correctAnswer: question.correctAnswer,
                isCorrect: isCorrect
            });
        });

        const result = new Result({
            quizId: quiz._id,
            score: score,
            totalQuestions: quiz.questions.length,
            submittedAnswers: resultDetails
        });

        await result.save();
        res.status(201).json({ resultId: result._id });

    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});

// GET a specific result for review
router.get('/results/:id', async (req, res) => {
    try {
        const result = await Result.findById(req.params.id).populate('quizId', 'title');
        if (!result) return res.status(404).json({ message: 'Result not found' });
        res.json(result);
    } catch (error) {
        res.status(500).json({ message: error.message });
    }
});


module.exports = router;