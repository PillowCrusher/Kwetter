import Home from './components/Home'
import Tweets from './components/Tweets'

import Login from './components/Login'
import Register from './components/Register'

// Manage pages
import TweetManage from './components/manage/Tweets'
import UserManage from './components/manage/Users'

// User pages
import Profile from './components/user/Profile'
import Following from './components/user/Following'
import Followers from './components/user/Followers'

var routes = {
	'/': {
		component: Home,
		title: 'Home'
	},

	// Authentication pages
	'/Login': {
		component: Login,
		title: 'Login to the App'
	},
	'/Register': {
		component: Register,
		title: 'Register to the App'
	},

	// Tweets page
	'/Tweets': {
		component: Tweets,
		title: 'Tweets overview'
	},

	// Manage pages
	'/Manage/Tweets': {
		component: TweetManage,
		title: 'Manage tweets overview'
	},
	'/Manage/Users': {
		component: TweetManage,
		title: 'Manage tweets overview'
	},

	// User pages
	'/User/Profile': {
		component: Profile,
		title: 'Profile overview'
	},
	'/User/Following': {
		component: Following,
		title: 'Following overview'
	},
	'/User/Followers': {
		component: Followers,
		title: 'Followers overview'
	}
}

module.exports = routes
