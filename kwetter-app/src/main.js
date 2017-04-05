import Vue from 'vue'
import App from './App.vue'

// Vue plugins
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import store from './store.js'

// Toastr messages
import toastr from 'toastr'

// Main css files
import './assets/css/bootstrap.min.css'
import './assets/font-awesome/css/font-awesome.min.css'
import './assets/css/animate.css'
import './assets/css/plugins/toastr/toastr.min.css'

// Main js files
import './assets/js/bootstrap.min.js'
import './assets/js/pace.min.js'
import './assets/js/plugins/slimscroll/jquery.slimscroll.min.js'

// Adding VueRouter, Vuex VueResource to the Vue instance
Vue.use(VueResource)
Vue.use(VueRouter)

Vue.mixin({
  methods: {
      /*
       * Shows error toastrMessage
       * Param: errorMessage - Message with error details
       */
      showErrorToastr (errorMessage) {
        setTimeout(function() {
              toastr.options = {
                  closeButton: true,
                  progressBar: true,
                  showMethod: 'slideDown',
                  positionClass: 'toast-bottom-right',
                  timeOut: 4000
              };
              toastr.error(errorMessage, 'Oops! Something went wrong!');
          }, 10);
      },

      /*
       * Shows success toastrMessage
       * Param: errorMessage - Message with success details
       */
      showSuccesToastr (successMessage) {
        setTimeout(function() {
          toastr.options = {
              closeButton: true,
              progressBar: true,
              showMethod: 'slideDown',
              positionClass: 'toast-bottom-right',
              timeOut: 4000
          };
          toastr.success(successMessage, 'Success!');
        }, 10);
      }
		}
	})

// Defining VueRouter settings
Vue.router = new VueRouter({
	//Turns off hashbang in the url
	hashbang: true,
	linkActiveClass: 'active',
})

// Mapping the routes.js file
Vue.router.map(require('./routes.js'))

// Any invalid route will redirect to home
Vue.router.redirect({
	'*': '/Login'
})

// Vue transition status check
Vue.router.beforeEach(function (transition) {
  transition.next()
  Vue.http.headers.common['Authorization'] = store.state.authenticationToken
})

// API URL
Vue.prototype.$apiurl = process.env.API_URL
Vue.http.options.root = process.env.API_URL

// Starting Vue.js on the App tag
Vue.router.start({
  store,
  components: { App }
}, '#app')
