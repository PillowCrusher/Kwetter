package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by robvangastel on 24/03/2017.
 */

@Named(value = "TweetBean")
@RequestScoped
public class TweetBean implements Serializable {

    @Inject
    private TweetService tweetService;

    public List<Tweet> getTweets() {
        return tweetService.findAll();
    }
}
