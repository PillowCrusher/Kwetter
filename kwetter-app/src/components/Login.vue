<template>
  <div class="text-center animated fadeInDown">
    <div class="row">
      <div class="col-xs-6 col-xs-offset-3">
        <div>
          <h1 class="logo-name">Kwetter</h1>
        </div>
        <p>Login in to see it in action.</p>
        <form role="form">
            <div class="form-group">
                <input type="text" class="form-control" v-model="username" placeholder="Username" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" v-model="password" placeholder="Password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width" @click="authenticate()">Login</button>

            <p class="text-muted text-center m-t-10">
              <small>Do not have an account?</small>
              <a class="btn btn-sm btn-white btn-block" v-link="'/Register'">Create an account</a>
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
      password: ''
		}
	},
  ready () {
    this.$store.commit("SET_AUTHENTICATIONTOKEN", "")
    this.$store.commit('SET_AUTHENTICATED', false)
  },
  methods: {
    authenticate () {
      this.$http.post( this.$apiurl + '/user/authenticate?username=' + this.username
      + '&password=' + this.password).then(response => {

        this.getUser()
        // Redirect
        // Create basic auth token
        var token = basic(this.username, this.password)

        // Set store variables
        this.$store.commit("SET_AUTHENTICATIONTOKEN", token)
        this.$store.commit('SET_AUTHENTICATED', true)
      }, response => {
        this.showErrorToastr(response.data.message)
      })
    },
    getUser () {
      this.$http.get( this.$apiurl + '/user/username?username=' + this.username).then(response => {
        var self = this;
        $.when(this.$store.commit("SET_CURRENTUSER", response.data)).done(function(data) {
          self.$route.router.go('/')
        })
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
