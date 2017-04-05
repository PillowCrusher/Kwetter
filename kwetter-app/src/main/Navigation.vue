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
           <input type="text" v-model="query" class="form-control" placeholder="Search">
         </div>
         <button type="submit" @click="searchTweet()" class="btn btn-default">Search</button>
        </form>
	      <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a v-if="!isAdmin" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Administrator settings <span class="caret"></span></a>
            <ul class="dropdown-menu">
              <li><a v-link="'/Manage/Users'">Manage users</a></li>
              <li><a v-link="'/Manage/Tweets'">Manage tweets</a></li>
            </ul>
          </li>
	        <li class="dropdown">
	          <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">{{myUsername}} <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	            <li><a v-link="'/User/' + myUserId + '/Profile'">My Profile</a></li>
	            <li role="separator" class="divider"></li>
	            <li><a v-link="'/Login'">Logout</a></li>
	          </ul>
	        </li>
	      </ul>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
</template>

<script>
	export default {
		data () {
			return {
				query: '',
			}
		},
		computed: {
			isAuthenticated () {
				return this.$store.state.authenticated
			},
			isAdmin () {
				if(this.$store.state.role == "ADMINISTRATOR") {
					return true
				}
				return false
			},
			myUsername () {
				return this.$store.state.currentUser.username
			},
			myUserId () {
				return this.$store.state.currentUser.id
			}
		},
		methods: {
			searchTweet () {
				if(this.query.length != 0) {
					this.$http.get( this.$apiurl + '/tweet/message?arg=' + this.query).then(response => {
						this.$store.commit("SET_SEARCHTWEETS", response.data)
						this.$router.go('/Tweets')
					}, response => {
						this.showErrorToastr(response.data.message)
					})
				}
			}
		}
	}
</script>
