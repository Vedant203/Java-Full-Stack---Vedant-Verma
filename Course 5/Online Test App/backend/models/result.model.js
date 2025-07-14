const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const ResultSchema = new Schema({
  quizId: { type: Schema.Types.ObjectId, ref: 'Quiz', required: true },
  score: { type: Number, required: true },
  totalQuestions: { type: Number, required: true },
  submittedAnswers: [{
    questionText: String,
    submittedAnswer: String,
    correctAnswer: String,
    isCorrect: Boolean
  }],
  submittedAt: { type: Date, default: Date.now }
});

module.exports = mongoose.model('Result', ResultSchema);