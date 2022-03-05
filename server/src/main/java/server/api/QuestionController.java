package server.api;

import commons.Question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.service.QuestionService;


@RestController
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionService service;

    @Autowired
    public QuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Question getById(@PathVariable("id") long id) {
        if (id < 0 || !service.existsById(id)) {
            return null;
        }
        return service.getId(id);
    }

//    @PostMapping("/")
//    public ResponseEntity<Question> add(@RequestBody Question question) {
//        if (!isQuestionValid(question))
//            return ResponseEntity.badRequest().build();
//        Question saved = service.addNewQuestion(question);
//        return ResponseEntity.ok(saved);
//    }

    static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    static boolean isQuestionValid(Question q) {
        if (isNullOrEmpty(q.question) || isNullOrEmpty(q.answer) ||
                isNullOrEmpty(q.wrongAnswer1) || isNullOrEmpty(q.wrongAnswer2))
            return false;
        return true;
    }


}