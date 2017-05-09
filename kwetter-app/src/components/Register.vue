<template>
  <div class="text-center animated fadeInDown">
    <div class="row">
      <div class="col-xs-6 col-xs-offset-3">
        <h3>Register to Kwetter</h3>
        <p>Create account to see it in action.</p>
        <form>
            <div class="form-group">
                <input type="text" class="form-control" v-model="username" placeholder="Name" required="">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" v-model="email" placeholder="Email" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" v-model="password" placeholder="Password" required="">
            </div>
            <button type="submit" @click="registerUser()" class="btn btn-primary block full-width">Register</button>

            <p class="text-muted text-center m-t-10"><small>Already have an account?</small>
            <a class="btn btn-sm btn-white btn-block" v-link="'/Login'">Login</a>
            </p>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import basic from 'basic-authorization-header'

export default {
	data () {
		return {
      username: '',
      password: '',
      email: ''
		}
	},
  ready () {
    this.$store.commit("SET_AUTHENTICATIONTOKEN", "")
    this.$store.commit('SET_AUTHENTICATED', false)
    this.$store.commit('SET_LOGGEDIN', false)
  },
  methods: {
    authenticate () {
      this.$http.post( this.$apiurl + '/user/authenticate?username=' + this.username
      + '&password=' + this.password).then(response => {
        // Redirect
        // Create basic auth token
        var token = basic(this.username, this.password)
        // Set store variables
        this.$store.commit("SET_AUTHENTICATIONTOKEN", token)
      }, response => {
        this.showErrorToastr(response.data.message)
      })
    },
    registerUser () {
      this.$http.post( this.$apiurl + '/user?username=' + this.username + '&email=' + this.email + '^&password=' + this.password).then(response => {
        var self = this
        this.$store.commit("SET_CURRENTUSER", response.data)
        this.authenticate()
        setTimeout(function() {
          self.$store.commit('SET_AUTHENTICATED', true)
          self.$store.commit('SET_LOGGEDIN', true)
          self.$route.router.go('/')
        }, 500)
      }, response => {
        this.showErrorToastr(response.data.message)
      })
    }
  },
  computed: {
    isAuthenticated () {
      return this.$store.state.authenticated
    }
  }
}
</script>


<style scoped>
.m-t-10 {
  margin-top: 10px;
}
</style>
