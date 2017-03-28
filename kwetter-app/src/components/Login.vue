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
                <input type="email" class="form-control" placeholder="Username" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width" v-link="'/'" @click="setAuthenticated(this.store)">Login</button>

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
import store from '../store'
import * as service from '../service'

export default {
	data () {
		return {
		}
	},
  methods: {
    setAuthenticated (authenticated) {
      service.setAuthenticated(store)
    }
  }
}
</script>

<style scoped>
.m-t-10 {
  margin-top: 10px;
}
</style>
