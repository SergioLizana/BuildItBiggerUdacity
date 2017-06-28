package com.example;

public class JokesLib {


    public static final String[] jokes = {
                "A man asks a farmer near a field, “Sorry sir, would you mind if I crossed your field instead of going around it? " +
                        "You see, I have to catch the 4:23 train. \n" +
                        "The farmer says, “Sure, go right ahead. And if my bull sees you, you’ll even catch the 4:11 one.",

                "What is the difference between a snowman and a snowwoman?\n" + "-\n" + "Snowballs",

                "I asked my daughter if she’d seen my newspaper. She told me that newspapers are old school. " +
                        "She said that people use tablets nowadays and handed me her iPad. The fly didn’t stand a chance.",

                "Anton, do you think I’m a bad mother?" +
                        "\n" +
                        "My name is Paul.",

                "Optimist: The glass is half full.\n" +
                        "\n" +
                        "Pessimist: The glass is half empty.\n" +
                        "\n" +
                        "Mother: Why didn’t you use a coaster!",

                "Sleep with an open window tonight! \n" +
                        "\n" +
                        "1400 mosquitos like that. 420 mosquitos commented on it. 210 mosquitos shared this. \n" +
                        "\n" +
                        "One mosquito invited for the event. 2800 mosquitos will be attending the event.",

                "Doctor: Do you do sports?\n" +
                        "\n" +
                        "Patient: Does sex count?\n" +
                        "\n" +
                        "Doctor: Yes.\n" +
                        "\n" +
                        "Patient: Then no.",

                        "Oh darling, since you’ve started dieting, you’ve become such a passionate kisser…\n" +
                        "\n" +
                        "What do you mean, passionate? I’m looking for food remains!"};


    public static String getJoke(){

        int random=(int)Math.floor(Math.random()*8);

        return jokes[random];

    }


    public static void main (String args[]){
        System.out.println(getJoke());
    }



}
