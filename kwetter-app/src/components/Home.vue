<template>
	<div class="animated fadeInRight">
		<div class="row">
			<div class="col-xs-4 col-xs-offset-1">
				<h4>What's happening?</h4>
				<div class="input-group">
					<textarea type="text" rows="4" cols="40" class="form-control" placeholder="Search for..."></textarea>
					<button class="btn btn-default m-t-10" type="button">Send</button>
					<p class="text-right">
						Character count: 0
					</p>
				</div><!-- /input-group -->
			</div>
			<div class="col-xs-6 col-xs-offset-1">
				<div class="row">
					<h4 class="text-left">&nbsp;&nbsp; Tweet amount: 90</h4>
					<div class="col-xs-4">
						<img alt="140x140" data-src="holder.js/140x140" class="img-rounded" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgdmlld0JveD0iMCAwIDE0MCAxNDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzE0MHgxNDAKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNWIwZmMzMjQxNiB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE1YjBmYzMyNDE2Ij48cmVjdCB3aWR0aD0iMTQwIiBoZWlnaHQ9IjE0MCIgZmlsbD0iI0VFRUVFRSIvPjxnPjx0ZXh0IHg9IjQ0LjA1NDY4NzUiIHk9Ijc0LjUiPjE0MHgxNDA8L3RleHQ+PC9nPjwvZz48L3N2Zz4=" data-holder-rendered="true" style="width: 140px; height: 140px;">
					</div>
					<div class="col-xs-6">
						<h5 class="text-left">Last tweet: blaablaa blaaa #Kwetter</h5>
					</div>
				</div>
				<div class="row m-t-10">
					<div class="col-xs-5">
						<h4>following: 20</h4>
					</div>
					<div class="col-xs-6">
						<h4>follower: 20</h4>
					</div>
				</div>
			</div>
			<div class="col-xs-10 col-xs-offset-1 m-t-50">
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
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>#</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Username</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">1</th>
										<td>Mark</td>
										<td>Otto</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<th scope="row">2</th>
										<td>Jacob</td>
										<td>Thornton</td>
										<td>@fat</td>
									</tr>
									<tr>
										<th scope="row">3</th>
										<td colspan="2">Larry the Bird</td>
										<td>@twitter</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="tab-pane fade" role="tabpanel" id="timeline" aria-labelledby="timeline-tab">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th>#</th>
										<th>First Name</th>
										<th>Last Name</th>
										<th>Username</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">1</th>
										<td>Mark</td>
										<td>Otto</td>
										<td>@mdo</td>
									</tr>
									<tr>
										<th scope="row">2</th>
										<td>Jacob</td>
										<td>Thornton</td>
										<td>@fat</td>
									</tr>
									<tr>
										<th scope="row">3</th>
										<td colspan="2">Larry the Bird</td>
										<td>@twitter</td>
									</tr>
								</tbody>
							</table>
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
			}
		},
		components: {

		},
		methods: {

		}
	}
</script>

<style scoped>
.m-t-10 {
	margin-top: 10px;
}

.m-t-50 {
	margin-top: 50px;
}

textarea {
    resize: none;
}
</style>
