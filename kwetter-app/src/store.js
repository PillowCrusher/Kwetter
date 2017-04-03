import Vuex from 'Vuex'
import Vue from 'vue'

Vue.use(Vuex)

// the root, initial state object
const state = {
	authenticated: false,
	authenticationToken: '',
	currentUser: {
		firstName: '',
		surname: '',
		role: '',
	}
}

// define the possible mutations that can be applied to our state
const mutations = {
	SET_CURRENTUSER (state, user) {
    state.currentUser = user
  },
	SET_AUTHENTICATED (state, authenticated) {
		state.authenticated = authenticated
	},
	SET_AUTHENTICATIONTOKEN (state, token) {
		state.authenticationToken = token
	}
}

const getters = {
    getUser: state => {
        return state.user
    }
}

const actions = {
    setUser(context, user) {
        context.commit('setCurrentUser', user)
    }
}

// create the Vuex instance by combining the state and mutations objects
// then export the Vuex store for use by our components
export default new Vuex.Store({
	state,
	getters,
	mutations,
	actions
})
