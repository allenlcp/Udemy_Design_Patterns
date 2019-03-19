package e003_challenge;

import java.util.List;

public class Client {
    public static void main(String[] args) {
        Supervisor techDean = new Supervisor("Mike", "Dean of Tech");
        Supervisor chairOfMathDept = new Supervisor("John", "Chair of Math Dept");
        Supervisor chairOfCSDept = new Supervisor("Ian", "Chair of CS Dept");

        Professor mathProf1 = new Professor("Math Prof1", "Adjunct", 302);
        Professor mathProf2 = new Professor("Math Prof2", "Associate", 402);

        Professor csProf1 = new Professor("CS Prof1","Adjunt", 100 );
        Professor csProf2 = new Professor("CS Prof2","Professor", 300 );
        Professor csProf3 = new Professor("CS Prof3","Associate", 800 );

        techDean.add(chairOfCSDept);
        techDean.add(chairOfMathDept);

        chairOfMathDept.add(mathProf1);
        chairOfMathDept.add(mathProf2);

        chairOfCSDept.add(csProf1);
        chairOfCSDept.add(csProf2);
        chairOfCSDept.add(csProf3);

        System.out.println("**COMPOSITE PATTERN DEMO**");
        System.out.println("\nThe college has the following structure\n");
        System.out.println(techDean.getDetails());

        List<Faculty> chairs = techDean.getMyFacultyList();
        for (Faculty f: chairs){
            System.out.println("\t" + f.getDetails());
        }


        List<Faculty> mathProf = chairOfMathDept.getMyFacultyList();
        for (Faculty m: mathProf){
            System.out.println("\t\t" + m.getDetails());
        }

        List<Faculty> csProf = chairOfCSDept.getMyFacultyList();
        for (Faculty c: csProf){
            System.out.println("\t\t" + c.getDetails());
        }

        chairOfCSDept.remove(csProf2);
        System.out.println("\nAfter CSE Prof2 leaving the org - CSE dept had the following prof");

        csProf = chairOfCSDept.getMyFacultyList();
        for (Faculty c: csProf){
            System.out.println("\t\t" + c.getDetails());
        }


    }
}
