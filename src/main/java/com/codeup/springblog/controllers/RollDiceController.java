package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class RollDiceController {

    @GetMapping("/roll-dice/")
    public String diceNum(Model diceModel){
        ArrayList<String> numbers = new ArrayList<>();
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        diceModel.addAttribute("numbers", numbers);
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable String n, Model diceModel) {
        int randomNumber = ThreadLocalRandom.current().nextInt(2,6 + 1);
        String numString = String.valueOf(randomNumber);

        diceModel.addAttribute("randomNumber", randomNumber);
        diceModel.addAttribute("number", n);


        if (n.equals(numString)) {
            diceModel.addAttribute("match", "Good guess!");
            diceModel.addAttribute("restart", true);
//            return "Your number matches";
        } else if (!n.equals(numString)){
            diceModel.addAttribute("success", "Roll again?");
            diceModel.addAttribute("restart", true);
//            return "Your number does not match";
        }
        return "roll-dice";
    }
}
