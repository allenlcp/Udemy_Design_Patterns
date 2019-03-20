package e001_chain_of_responsibility;

import java.util.Scanner;

public class Client {
    private DispenseChain c1;

    public Client() {
        // initialize the chain
        this.c1 = new Dollar50();
        DispenseChain c2 = new Dollar20();
        DispenseChain c3 = new Dollar10();

        // set the chain of responsibility;
        c1.setNextChain(c2);
        c2.setNextChain(c3);
    }

    public static void main(String[] args) {
        Client atm = new Client();
        while(true){
            int amount = 0;

            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();

            if (amount % 10 != 0){
                System.out.println("Amount should be in multiple of 10s");
                return;
            }

            // process the request
            atm.c1.dispense(new Currency(amount));
        }
    }
}
