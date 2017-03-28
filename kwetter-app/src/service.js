import store from './store.js'

const apiurl = process.env.API_URL

/*
 * Returns current logged in employee and stores it in store.js
 */
export function setAuthenticated ({dispatch}) {
  dispatch('SET_AUTHENTICATED', true)
}
