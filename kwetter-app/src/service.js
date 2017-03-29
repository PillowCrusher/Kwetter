import store from './store.js'

const apiurl = process.env.API_URL

/*
 * Sets whether the user's authentication was valid
 */
export function setAuthenticated ({dispatch}) {
  dispatch('SET_AUTHENTICATED', true)
}

/*
 * Sets the authenitication token for the logged in user
 */
 export function setAuthenticationToken ({dispatch}, token) {
   dispatch('SET_AUTHENTICATIONTOKEN', token)
 }
