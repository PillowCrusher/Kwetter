package com.robvangastel.kwetter.dao;

import com.robvangastel.kwetter.domain.User;

public interface IUserDao {

	/***
	 *
	 * @param username
	 * @return
     */
	User getUserbyUsername(String username);

	/***
	 *
	 * @param follower
	 * @param followee
     */
	void insertFollower(User follower, User followee);

	/***
	 *
	 * @param follower
	 * @param followee
     */
	void deleteFollower(User follower, User followee);

	/***
	 *
	 * @param follower
	 * @param followee
     * @return
     */
	boolean isUserFollower(User follower, User followee);

	/***
	 *
	 * @param user
     */
	void registerUser(User user);
}
