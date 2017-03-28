import Vuex from 'Vuex'
import Vue from 'vue'

Vue.use(Vuex)

var store = new Vuex.Store({
	// Initial state
	state: {
		authenticated: true,
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
		}
	},
	strict: false // Vuex's patent pending anti-intern device
})

export default store
