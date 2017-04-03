<template>
	<div class="animated fadeInRight">
		<div class="row">
			<div class="col-xs-12 col-lg-3 col-md-3 col-md-offset-1 col-lg-offset-1">
				<div class="panel panel-default">
					<div class="panel-heading">Profile</div>
					<div class="panel-body">
						<img alt="140x140" data-src="holder.js/140x140" class="img-rounded" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNWIwZmMzMjQxNiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1YjBmYzMyNDE2Ij48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true" style="width: 140px; height: 140px;">
						<div class="row m-t-10">
							<h5>Tweet amount: 90</h5>
							<h5>following: 20</h5>
							<h5>follower: 20</h5>
							<h5>last tweet: asdasdasdsad</h5>
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-7 col-lg-7">
				<h4>What's happening?</h4>
				<div class="input-group">
						<div class="input-group">
							<textarea type="text" rows="3" v-model="message" cols="40" class="form-control" placeholder="Search for..."></textarea>
							<span class="input-group-addon btn btn-primary disabled" id="sendTweet" type="button">Send</span>
						</div>
						<div class="text-left">
							Character count: {{ messageLength }}
					</div>
				</div><!-- /input-group -->
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12 col-lg-9 col-md-9 col-md-offset-1 col-lg-offset-1 m-t-30">
				<div class="bs-example bs-example-tabs" data-example-id="togglable-tabs">
					<ul class="nav nav-tabs" id="myTabs" role="tablist">
						<li role="presentation" class="active">
							<a href="#timeline" id="timeline-tab" role="tab" data-toggle="tab" aria-controls="timeline" aria-expanded="true">Timeline</a>
						</li>
						<li role="presentation" class="">
							<a href="#metions" role="tab" id="mentions-tab" data-toggle="tab" aria-controls="mentions" aria-expanded="false">@Mentions</a>
						</li>
 					</ul>
					<div class="tab-content" id="timelineTabContent">
						<div class="tab-pane fade active in" role="tabpanel" id="timeline" aria-labelledby="timeline-tab">
							<div class="m-t-10">
								<div class="panel panel-default">
								  <div class="panel-heading">Name + Tag</div>
								  <div class="panel-body">Tweet message, message</div>
								</div>
							</div>
						</div>
						<div class="tab-pane fade" role="tabpanel" id="timeline" aria-labelledby="timeline-tab">
							<div class="m-t-10">
								<div class="panel panel-default">
									<div class="panel-heading">Name + Tag</div>
									<div class="panel-body">Tweet message, message</div>
								</div>
							</div>
						</div>
				 </div>
			 </div>
			</div>
		</div>
	</div>
</template>

<script>
	export default {
		ready() {

		},
		data () {
			return {
				people: [],
				message: '',
				validMessage: true,
			}
		},
		components: {

		},
		computed: {
			messageLength() {
				if(this.message.length > 140) {
					if(!$("#sendTweet").hasClass("disabled")) {
						$("#sendTweet").addClass("disabled")
					}

					this.validMessage = false
					return this.message.length
				}

				if(this.message.length == 0) {
					if(!$("#sendTweet").hasClass("disabled")) {
						$("#sendTweet").addClass("disabled")
					}

					this.validMessage = false
					return 0
				}

					$("#sendTweet").removeClass("disabled")
				this.validMessage = true
				return this.message.length
			}
		},
		methods: {
			sendTweet() {
				if(this.validMessage) {

				}
			}
		}
	}
</script>

<style scoped>
.m-t-10 {
	margin-left: 5px;
	margin-top: 10px;
}

.m-t-30 {
	margin-top: 30px;
}

textarea {
    resize: none;
}
</style>
