package com.Teacher.Controller;
import com.Teacher.Model.QuestionDao;
import com.Teacher.Model.QuizData;
import com.Teacher.Others.AllQuizData;
import com.Teacher.Others.ConductedQuizByTeacher;
import com.Teacher.Repository.QuizRepo;
import com.Teacher.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/teachers")
public class TeacherClass {
    @Autowired
    TeacherService teacherService;
    @Autowired
    QuizRepo quizRepo;
    @GetMapping("category/{category}")
    private ResponseEntity<List<QuestionDao>> getQuestionByCategory(@PathVariable String category) {
        return teacherService.getQuestionsByCategory(category);
    }
    @GetMapping("allquestions")
    private ResponseEntity<List<QuestionDao>> getAllQuestions() {
        return teacherService.getAllQuestions();
    }
    @GetMapping("randquestions")
    private ResponseEntity<List<QuestionDao>> getRandomQuestions(@RequestParam String category, @RequestParam int numQ) {
        return teacherService.findRandQuestions(category, numQ);
    }
    @PostMapping("createQuiz")
    private ResponseEntity<String> createQuiz(@RequestBody AllQuizData data){
        return teacherService.createQuiz(data);
    }
    @GetMapping("conductedQuestions/{teachername}")
    private ResponseEntity<List<ConductedQuizByTeacher>> getConductedQuestions(@PathVariable String teachername) {
        return teacherService.getConductedQuestions(teachername);
    }
    @GetMapping("questionsforquiz/{quizid}")
    private ResponseEntity<List<QuestionDao>> getQuestionsforQuiz(@PathVariable Integer quizid) {
        return teacherService.getQuestionsforQuiz(quizid);
    }
    @GetMapping("getemailsofstudents")
    private ResponseEntity<List<String>>getEmailsofconductedStudents(@RequestParam String teachername, @RequestParam String quizname) {
        return teacherService.getEmailsOfParticularTeacher(teachername,quizname);
    }
    @GetMapping("get-quizzes-byemail/{email}")
    public List<QuizData> getQuizzesSharedWithEmail(@PathVariable String email) {
        return teacherService.findQuizzesByStudentEmail(email);
    }
}