package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String diceNum(Model diceModel){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String diceRoll(@PathVariable String n, Model diceModel) {
        int randomNumber = ThreadLocalRandom.current().nextInt(1,6 + 1);
        String numString = String.valueOf(randomNumber);

        diceModel.addAttribute("randomNumber", randomNumber);
        diceModel.addAttribute("number", n);


        if (n.equals(numString)) {
            diceModel.addAttribute("match", "Good guess!");
            diceModel.addAttribute("restart", true);
        } else if (!n.equals(numString)){
            diceModel.addAttribute("success", "Roll again?");
            diceModel.addAttribute("restart", true);
        }
        return "roll-dice";
    }
}
