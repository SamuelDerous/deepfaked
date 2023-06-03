/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.controls.PropertiesHolder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import org.json.JSONObject;

/**
 * The OpenAI functionallity on the mainscreen.
 * @author ZENODotus
 */

public class ChatGPT {
    private static final String API_KEY = PropertiesHolder.getInstance().getProperty("gptKey"); //"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";
    
    public static String chatGPT(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 100);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        return (new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"));
    }
         // Set up the completion request
        /*CompletionRequest completionRequest = new CompletionRequest();
        completionRequest.setPrompt("Write a short story about a robot that learns to love");
        completionRequest.setMaxTokens(100);
        List<String> modelList = new ArrayList<>();
        modelList.add("text-davinci-002");
        Engine engine = new Engine();
        engine.setModelList(modelList);
        completionRequest.setEngine(engine);

        // Send the completion request to the OpenAI API
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), new Gson().toJson(completionRequest));
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/engines/text-davinci-002/completions")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .post(requestBody)
                .build();
        Response response = httpClient.newCall(request).execute();

        // Check for errors in the response
        if (!response.isSuccessful()) {
            System.out.println("Error: " + response.code() + " " + response.message());
            return;
        }

        // Parse the response and extract the generated text
        OpenAIResponse openAIResponse = new Gson().fromJson(response.body().string(), OpenAIResponse.class);
        CompletionResponse completionResponse = new Gson().fromJson(openAIResponse.getData().toString(), CompletionResponse.class);
        String generatedText = completionResponse.getChoices().get(0).getText();

        // Check the length of the generated text
        int generatedTextLength = generatedText.split("\\s+").length; // Split the text by whitespace to count the number of tokens
        if (generatedTextLength > completionRequest.getMaxTokens()) {
            System.out.println("Warning: Generated text exceeds max_tokens parameter (" + generatedTextLength + " tokens)");
        }

        // Print the generated text to the console
        System.out.println(generatedText);*/
    //}

    public static void main(String[] args) throws Exception {
        chatGPT("Ken je Nederlands?");
    }
}