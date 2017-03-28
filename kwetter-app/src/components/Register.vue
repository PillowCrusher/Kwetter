<template>
  <div class="text-center animated fadeInDown">
    <div class="row">
      <div class="col-xs-6 col-xs-offset-3">
        <h3>Register to Kwetter</h3>
        <p>Create account to see it in action.</p>
        <form>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Name" required="">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" placeholder="Email" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" required="">
            </div>
            <button type="submit" class="btn btn-primary block full-width">Register</button>

            <p class="text-muted text-center m-t-10"><small>Already have an account?</small>
            <a class="btn btn-sm btn-white btn-block" v-link="'/Login'">Login</a>
            </p>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.m-t-10 {
  margin-top: 10px;
}
</style>
