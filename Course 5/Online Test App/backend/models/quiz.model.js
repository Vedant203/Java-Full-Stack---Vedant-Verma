const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const QuestionSchema = new Schema({
  questionText: { type: String, required: true },
  options: [{ type: String, required: true }],
  correctAnswer: { type: String, required: true }
});

const QuizSchema = new Schema({
  title: { type: String, required: true },
  description: { type: String },
  questions: [QuestionSchema]
});

module.exports = mongoose.model('Quiz', QuizSchema);