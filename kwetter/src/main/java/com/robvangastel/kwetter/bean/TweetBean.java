package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Rob on 2-4-2017.
 */

@Named(value = "tweetBean")
@SessionScoped
public class TweetBean implements Serializable {

    @Inject
    private TweetService tweetService;

    public List<Tweet> getTweets() {
        return tweetService.findAll();
    }

    public void deleteTweet(long id) throws Exception {
        tweetService.deleteById(id);
    }
}