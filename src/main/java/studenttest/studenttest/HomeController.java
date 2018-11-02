package studenttest.studenttest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    StudentTestRepository studentTestRepository;


    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/")
    public String homePage(){
        return "homepage";
    }

    @RequestMapping("/studentform")
    public String studentForm(Model model){
        model.addAttribute("thisstudent", new Student());
        return "studentform";
    }

    @PostMapping("/processstudent")
    public String processStudent(@Valid Student student, BindingResult result){
        if(result.hasErrors()){
            return "studentform";
        }
        studentRepository.save(student);
        return "homepage";
    }

    @RequestMapping("/studenttestform")
    public String testform(Model model){
        model.addAttribute("studetTest", new StudentTest());
        model.addAttribute("students", studentRepository.findAll());
        return "studenttestform";
    }

    @PostMapping("/processstudenttestform")
    public String addStudentTest(@Valid StudentTest studentTest, BindingResult result){
        if (result.hasErrors()){
            return "studenttestform";
        }
        studentTestRepository.save(studentTest);
        return "homepage";
    }

    @RequestMapping("showlist")
    public String showList(Model model){
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("tests", studentTestRepository.findAll());
        return "showlist";
    }

}
