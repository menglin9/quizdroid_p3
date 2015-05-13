package quizdroid.menglin9.washington.edu.quiz_p3;

import android.app.Application;
import android.app.ProgressDialog;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Menglin on 5/10/15.
 */

public class QuizApp extends Application implements TopicRepository {

    private static QuizApp sInstance = null;

    // URL to get contacts JSON
    //private static String url = "http://tednewardsandbox.site44.com/questions.json";
    //private ProgressDialog pDialog;
    public ArrayList<Topic> topicList;

    // JSON Node names
    private static final String TAG_title = "title";
    private static final String TAG_desc = "desc";
    private static final String TAG_questions = "questions";
    private static final String TAG_text = "text";
    private static final String TAG_answer = "answer";
    private static final String TAG_answers = "answers";

    // contacts JSONArray
    JSONObject jsonQuiz = null;
    boolean JsonSuccess = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("app", "QuizApp has been loaded and run!!!");

        //get json data
        String json = null;

        // Fetch data.json in assets/ folder
        try {
            InputStream inputStream = getAssets().open("data.json");
            json = readJSONFile(inputStream);

            JSONArray jsonArray = new JSONArray(json);
            //JSONObject jsonObj = new JSONObject(jsonStr);

            JsonSuccess = true;
            //Topic topicNew = new Topic();
            //fetch out each topic object
            for (int i = 0; i < jsonArray.length(); i++) {
                Topic topicNew = new Topic();
                JSONObject c = jsonArray.getJSONObject(i);

                String title = c.getString(TAG_title);
                String desc = c.getString(TAG_desc);

                topicNew.setTitle(title);
                topicNew.setShortD(title);
                topicNew.setLongD(desc);

                //Question node is Json Array
                JSONArray question = c.getJSONArray(TAG_questions);
                ArrayList<Quiz> quizListA = new ArrayList<Quiz>();

                //fetch out each quiz object
                for (int j = 0; j < question.length(); j++) {
                    //implement each quiz object
                    ArrayList<String> answersList = new ArrayList<String>();
                    Quiz quizNew = new Quiz();

                    JSONObject eachQ = question.getJSONObject(j);
                    String text = eachQ.getString(TAG_text);
                    String answer = eachQ.getString(TAG_answer);
                    JSONArray answers = eachQ.getJSONArray(TAG_answers);

                    //deal with each quiz object
                    quizNew.setText(text);
                    quizNew.setCorrect(Integer.parseInt(answer));

                    //put into answers arraylist
                    int len = answers.length();
                    for (int k = 0; k < len; k++) {
                        answersList.add(answers.get(k).toString());
                    }
                    quizNew.setAnswers(answersList);
                    //adding to quizArrayList
                    quizListA.add(quizNew);

                }
                //adding to topicArrayList
                topicNew.setIcon(R.drawable.ic_launcher);
                topicNew.setQuizList(quizListA);
                topicList.add(topicNew);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // reads InputStream of JSON file and returns the file in JSON String format
    public String readJSONFile(InputStream inputStream) throws IOException {

        int size = inputStream.available();
        byte[] buffer = new byte[size];
        inputStream.read(buffer);
        inputStream.close();

        return new String(buffer, "UTF-8");
    }

    public QuizApp() {
        if (sInstance == null) {
            sInstance = this;
        } else {
            throw new RuntimeException("cannot create more than one QuizApp! ");
        }

        topicList = new ArrayList<Topic>();
    }

    public String[] getAllTopics() {
        int length = topicList.size();
        String[] topics = new String[length];
        for (int i = 0; i < length; i++) {
            topics[i] = topicList.get(i).getShortD();
        }
        return topics;
    }

    public boolean getSuccess() {
        return JsonSuccess;
    }

    public int topicNum() {
        return topicList.size();
    }

    //return title of given topic (by index)
    public String titleName(int i) {
        return topicList.get(i).getTitle();
    }

    //return longDescription of given topic (by index)
    public String longDescription(int i) {
        return topicList.get(i).getLongD();
    }

    //return questionNum of given topic
    public int questionNum(int i) {
        return topicList.get(i).getQuiz().size();
    }

    //return the current question in topic i and question index j
    public String getCurrentQuestion(int i, int j) {
        return topicList.get(i).getQuiz().get(j).getText();
    }

    //return all the answers as arraylist of certain topic i and certain question j
    public ArrayList<String> getAllAnswers(int i, int j) {
        return topicList.get(i).getQuiz().get(j).getAnswers();
    }

    //return correct answer of certain topic i and certain question j
    public String getCorrectAnswer(int i, int j) {
        return topicList.get(i).getQuiz().get(j).getAnswer();
    }

    public int[] getAllIcons() {
        int length = topicList.size();
        int[] icons = new int[length];
        for (int i = 0; i < length; i++) {
            icons[i] = topicList.get(i).getIcon();
        }
        return icons;
    }

    public int getIcons(int i) {
        return topicList.get(i).getIcon();
    }

/*
    public void addTopic() {
        Topic newTopic = new Topic();
        topic.add(newTopic);
    }


    public void setTopic(Topic topic) {
        //this.topic = topic;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz =quiz;
    }

    public Quiz getAllQuiz() {
        return quiz;
    }

    public Topic getTopic() {
        //return topic;
    }

    public void setTitle(String title) {
        topic.setTitle(title);
    }

*/

}
