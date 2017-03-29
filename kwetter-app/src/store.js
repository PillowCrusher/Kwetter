import Vuex from 'Vuex'
import Vue from 'vue'

Vue.use(Vuex)

var store = new Vuex.Store({
	// Initial state
	state: {
		authenticated: false,
		authenticationToken: '',
		currentUser: {
			firstName: '',
			surname: '',
			role: '',
		}
	},
	mutations: {
		SET_CURRENTUSER (state, user) {
      state.currentUser = user
    },
		SET_AUTHENTICATED (state, authenticated) {
			state.authenticated = authenticated
		},
		SET_AUTHENTICATIONTOKEN (state, token) {
			state.authenticationToken = token
		}
	},
	strict: false // Vuex's patent pending anti-intern device
})

export default store
