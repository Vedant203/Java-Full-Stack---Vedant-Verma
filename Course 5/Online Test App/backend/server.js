require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const cors = require('cors');
const Quiz = require('./models/quiz.model');

const app = express();
const PORT = process.env.PORT || 5000;

// Middleware
app.use(cors());
app.use(express.json());

// --- THIS IS THE FUNCTION TO UPDATE ---
const seedDatabase = async () => {
    try {
        await Quiz.deleteMany({}); // Clear existing quizzes

        // --- PASTE YOUR NEW QUIZZES HERE ---
        const sampleQuizzes = [
            {
              "title": "Indian History Quiz",
              "description": "Test your knowledge of India's rich historical past.",
              "questions": [
                {
                  "questionText": "Who was the first Emperor of the Maurya Dynasty?",
                  "options": ["Ashoka", "Chandragupta Maurya", "Bindusara", "Harshavardhana"],
                  "correctAnswer": "Chandragupta Maurya"
                },
                {
                  "questionText": "Which Mughal emperor built the Taj Mahal?",
                  "options": ["Akbar", "Babur", "Shah Jahan", "Aurangzeb"],
                  "correctAnswer": "Shah Jahan"
                },
                {
                  "questionText": "When did India gain independence from British rule?",
                  "options": ["1945", "1947", "1950", "1930"],
                  "correctAnswer": "1947"
                }
              ]
            },
            {
              "title": "India Capital Challenge",
              "description": "How well do you know the capitals of Indian states?",
              "questions": [
                {
                  "questionText": "What is the capital of Uttar Pradesh?",
                  "options": ["Lucknow", "Kanpur", "Varanasi", "Agra"],
                  "correctAnswer": "Lucknow"
                },
                {
                  "questionText": "What is the capital of Tamil Nadu?",
                  "options": ["Chennai", "Madurai", "Coimbatore", "Tiruchirappalli"],
                  "correctAnswer": "Chennai"
                }
              ]
            }
        ];

        // This is the correct way to insert an array of documents
        await Quiz.insertMany(sampleQuizzes);
        console.log('Database has been seeded successfully with new quizzes!');

    } catch (error) {
        console.error('Error seeding the database:', error);
    }
};

// DB Connection
mongoose.connect(process.env.MONGO_URI)
    .then(() => {
        console.log('MongoDB connected successfully.');
        if (process.env.NODE_ENV === 'development') {
            seedDatabase();
        }
    })
    .catch(err => console.error('MongoDB connection error:', err));


// Routes
const apiRoutes = require('./routes/api');
app.use('/api', apiRoutes);

// Start Server
app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
