package contoller;

import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class RandomCodeGenarator {
    public int getRandomCode() {
        return new Random().nextInt(1000000) + 0;
    }
}
