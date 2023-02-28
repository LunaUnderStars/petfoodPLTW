/*
 * Problem 2 Sell My Pet Food
 */

import java.util.ArrayList;
import java.util.Arrays;

public class TargetedAd {

    public static void main(String[] args)
    {
      /*  
       * TODO:
       * PREPARATION WORK
       * (1) Create a file called targetWords.txt. Populate this file with words on each line that
       *     you think would determine if a user is a dog or cat owner.
       * 
       * PROGRAMMING
       * (2) Create a new DataCollector object and set the data to "socialMediaPostsSmall.txt" and "targetWords.txt"
       *     Important: Use the socialMedialPostsSmall to create your algorithm. Using a small file will help you 
       *     generate your solution quicker and give you the ability to double check your work.
       * (3) Create a String variable to hold the names of all the user. (The first word of every post is 
       *     a person's username)
       * (4) Compare each user's post to each target word. If a user mentions a target word, add their username to 
       *     the String of users. Separate usernames with a space. 
       *         Hint: You can use loops to look through each word. 
       *         Hint2: You can use indexOf to check if a word is in a user post. 
       * (5) Once you have all the users, use your DataCollector's prepareAdvertisement method to prepare a file 
       *     with all users and the advertisement you will send them.
       *         Additional Info: The prepareAdvertisement creates a new file on your computer. Check the posts of
       *         some of the usernames to make sure your algorithm worked.
       * 
       * THE FINAL SOLUTION
       * (6) Your solution should work with the socialMedialPostsSmall.txt. Modify your DataCollector initialization
       *    so you use the socialMediaPosts.txt. You should now have a larger file of users to target.
       */
  
  
      /* your code here */
      DataCollector dc = new DataCollector();
      dc.setData("socialMediaPostsSmall.txt", "targetedWords.txt");


      String post;
      String[] postArray;
      ArrayList<String> postArrayList;
      String username;

      String adUsers = "";


      //populate keywordArray with target words
      ArrayList<String> keywordArray = new ArrayList<String>();
      Boolean wordsPopulated = false;
      String targetWord;
      while (wordsPopulated == false) {
        targetWord = dc.getNextTargetWord();
        if (!targetWord.equals("NONE")) {
          keywordArray.add(targetWord);
        }
        else {
          wordsPopulated = true;
        }

      }

      while (true) {
        //load next post
        post = dc.getNextPost();
        //if posts are completed, break out
        if (post.equals("NONE")) {
          break;
        }
        //strip quotation marks, split into individual words, capture username, and remove username from list of words
        postArray = post.replaceAll("\"", "").split("\\s+");
        username = postArray[0];
        postArrayList = new ArrayList<String>(Arrays.asList(postArray));
        System.out.println("Username is " + username);
        postArrayList.remove(0);
        
        //check if loaded post contains keywords, add username to list if so.
        if (inPost(postArrayList, keywordArray) == true) {
          adUsers += (username + " ");
          System.out.println("Username Added to list\nCurrent list: " + adUsers);
        }
      }
      System.out.println("Post evaluation complete. Final list of users : " + adUsers);
    }

    //Pass in a post seperated into seperate words and a list of keywords. returns true if a keyword is found within post, false otherwise
    public static boolean inPost(ArrayList<String> postWords, ArrayList<String> keywords) {
      for (String word : postWords) {
        System.out.println(word);
        for (String keyword : keywords) {
          System.out.println("Matching with " + keyword);
          if (word.toLowerCase().equals(keyword)) {
            return true;
          }
        }
      }
      return false;
    }
  }
  