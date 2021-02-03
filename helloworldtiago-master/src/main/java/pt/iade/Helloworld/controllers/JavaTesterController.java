package pt.iade.Helloworld.controllers;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.Helloworld.models.CurricularUnit;

@RestController
@RequestMapping(path="/api/java/tester")
public class JavaTesterController{
    @GetMapping(path = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor()
    {
        String nome="Tiago Oliveira";
        double altura=1.77;
        int numero=50039852;
        boolean is_Fan=true;
        String clube="Sporting";
        if(is_Fan){
            return("Done by"+nome+"whit number"+numero+".\n"+"I am"+altura+"tall and I am a fan of football."+".\n"+"My favourite club is"+clube+".");
        }
        else if(!is_Fan){
            return("Done by"+nome+"whit number"+numero+".\n"+"I am"+altura+"tall and I am a fan of football.");
        }
        return clube;
    }

    private Logger logger= LoggerFactory.getLogger(JavaTesterController.class);

    private ArrayList<CurricularUnit> units= new ArrayList<CurricularUnit>();
    
    @PostMapping(path = "/units",produces = MediaType.APPLICATION_JSON_VALUE)
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit){
        logger.info("Added unit"+ unit.getName());
        units.add(unit);
        return unit;
    }
    @GetMapping(path = "/units",
    produces= MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get "+units.size()+" Units");
        return units;
    } 
    private double grade[]={10.5,12,14.5};
    private static String ucs[] = {"FP","POO","BD"};
    @GetMapping(path = "/grademax", produces = MediaType.APPLICATION_JSON_VALUE)
    public double grademax(){
        double max = 0;
        for(int i=1;i < grade.length;i++){
            if(grade[i]>max){
                max=grade[i];
            }
        }
        return max;
    }
    @GetMapping(path ="/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public double average(){
        double notas_finais=0;
        for(int i=1 ; i<grade.length;i++){
            notas_finais= notas_finais+grade[i];
        }
        double average=notas_finais/grade.length;
        return average;
    }
    @GetMapping(path ="/grade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double Grade() {
        String cu = "POO";
        double cugrade = 0;
        for (int i = 1; i < ucs.length; i++) {
            if (ucs[i].equals(cu)) {
                cugrade = grade[i];
            }
        }
        return cugrade;
    }

    @GetMapping(path = "access/{student}/{covid}",produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, @PathVariable("covid") boolean hasCovid) {
        if(isStudent && (!hasCovid)){
            return true;
        }else{
            return false;
        }
    }
    @GetMapping(path ="/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, @PathVariable("temperature") double hasCovid, @PathVariable("classType") String type) {
        if (isStudent && type.equals("presential") && (hasCovid < 37.5 && hasCovid > 34.5)) {
            return true;
        } else {
            return false; 
        }
    }

    @GetMapping(path ="/evacuation/{fire}/{numberOfCovids}/{powerShutdown}/{comeBackTime}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getEvacuation(@PathVariable("fire") boolean fire, @PathVariable("numberOfCovids") int numberOfCovids, @PathVariable("powerShutdown") boolean powerShutdown, @PathVariable("comeBackTime") int comeBack) {
        if (fire) {
            return true;
        } else if (numberOfCovids > 5) {
            return true;
        } else if (powerShutdown && comeBack > 15) {
            return true;
        }
        return false;
    }

}