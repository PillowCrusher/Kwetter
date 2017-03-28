<template>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" v-link="'/'">Kwetter</a>
	    </div>

	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <form class="navbar-form navbar-left">
         <div class="form-group">
           <input type="text" class="form-control" placeholder="Search">
         </div>
         <button type="submit" class="btn btn-default" v-link="'/Tweets'">Submit</button>
        </form>
	      <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administrator settings <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a v-link="'/Manage/Users'">Manage users</a></li>
              <li><a v-link="'/Manage/Tweets'">Manage tweets</a></li>
            </ul>
          </li>
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">User <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a v-link="'/User/Profile'">Profile</a></li>
	            <li><a v-link="'/User/Following'">Following</a></li>
	            <li><a v-link="'/User/Followers'">Followers</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a v-link="'/Login'">Logout</a></li>
	          </ul>
	        </li>
					<form class="navbar-form navbar-left">
						<button type="submit" v-if="!isAuthenticated" class="btn btn-default" v-link="'/Login'">Login</button>
					</form>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
</template>

<script>
	import store from '../store'

	export default {
		computed: {
			isAuthenticated () {
				return store.state.authenticated
			},
		}
	}
</script>
