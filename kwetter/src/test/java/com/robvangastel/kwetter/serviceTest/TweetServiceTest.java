/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.service.TweetService;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


/**
 *
 * @author robvangastel
 */

public class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;

	@Mock
    private ITweetDao tweetDao;

    @Before
    public void setUp() {
	    initMocks(this);
    }

	@Test
	public void ServiceTest() {
		assertNotNull(tweetService);
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public Tweet create(Tweet tweet) throws Exception;
	public void createTest() throws Exception {
		// Case 1 - Existing User
		// Case 2 - Non-existing User
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void delete(long id, long userId) throws Exception;
	public void deleteTest() throws Exception {
		// Case 1 - Existing User
		// Case 2 - Non-existing User
	}
}
