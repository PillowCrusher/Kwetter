<template>
  <div class="animated fadeInRight">
		<div class="row">
			<div class="col-xs-10 col-xs-offset-1">
        <table class="table table-condensed">
          <thead>
            <tr>
              <th>#</th>
              <th>username</th>
              <th>email</th>
              <th>message</th>
              <th>timestamp</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="tweet in tweets">
              <th scope="row">{{tweet.id}}</th>
              <td>{{tweet.username}}</td>
              <td>{{tweet.email}}</td>
              <td>{{tweet.message}}</td>
              <td>{{tweet.timestamp}}</td>
              <th>
                <div class="btn-group">
                  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Action <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu">
                    <li><a @click="viewProfile(tweet.user_id)">View profile</a></li>
                    <li><a @click="deleteTweet(tweet.id)">Delete tweet</a></li>
                  </ul>
                </div>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
	export default {
    data() {
      return {
        tweets: [],
      }
    },
    ready () {
      this.getTweets()
    },
		methods: {
			getTweets () {
        this.$http.get( this.$apiurl + '/tweet').then(response => {
          this.tweets = response.data
        }, response => {
          this.showErrorToastr(response.data.message)
        })
			},
      deleteTweet(id) {
        this.$http.delete( this.$apiurl + '/tweet/' + id).then(response => {
          this.getTweets()
        }, response => {
          this.showErrorToastr(response.data.message)
        })
      },
      viewProfile(id) {
        console.log('/User/' + id + '/Profile');
        this.$router.go('/User/' + id + '/Profile')
      }
		}
	}
</script>
