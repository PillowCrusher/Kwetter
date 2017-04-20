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
              <th>role</th>
              <th>bio</th>
              <th>location</th>
              <th>website</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="user in users">
              <th scope="row">{{user.id}}</th>
              <td>{{user.username}}</td>
              <td>{{user.email}}</td>
              <td>{{user.role}}</td>
              <td>{{user.bio}}</td>
              <td>{{user.location}}</td>
              <td>{{user.websiteUrl}}</td>
              <th>
                <div class="btn-group">
                  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Action <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu">
                    <li><a @click="viewProfile(user.id)">View profile</a></li>
                    <li><a class="content-visible"  @click="setUser(user)" data-toggle="modal" data-target="#UserModal">Edit role</a></li>
                    <li><a @click="deleteUser(user.id)">Delete user</a></li>
                  </ul>
                </div>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div class="modal fade" id="UserModal" tabindex="-1" role="dialog" aria-labelledby="UserModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="UserModalLabel"><i class="fa fa-pencil"></i> Edit role</h4>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="message-text" class="control-label">Select the new role</label>
              <select class="form-control" v-model="selectedRole" id="message-text">
                  <option v-for="role in roles" v-bind:value="role">
                    {{ role }}
                  </option>
              </select>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">close </button>
          <button @click="editRole()" type="button" class="btn btn-primary" data-dismiss="modal">edit </button>
        </div>
      </div>
    </div>
</div>
</template>

<script>
	export default {
    data() {
      return {
        users: [],

        // Modal variables
        selectedUser: '',
        selectedRole: 'USER',
        roles: ['USER', 'MODERATOR', 'ADMINISTRATOR'],
      }
    },
    ready () {
      this.getUsers()
    },
		methods: {
      setUser(user) {
        this.selectedUser = user
      },
			getUsers () {
        this.$http.get( this.$apiurl + '/user').then(response => {
          this.users = response.data
        }, response => {
          this.showErrorToastr(response.data.message)
        })
			},
      deleteUser(id) {
        this.$http.delete( this.$apiurl + '/user/' + id).then(response => {
          this.getUsers()
        }, response => {
          this.showErrorToastr(response.data.message)
        })
      },
      editRole() {
        this.$http.put( this.$apiurl + '/user/' + this.selectedUser.id + '?role=' + this.selectedRole).then(response => {
          this.getUsers()
        }, response => {
          this.showErrorToastr(response.data.message)
        })
      },
      viewProfile(id) {
        this.$router.go('/User/' + id + '/Profile')
      }
		}
	}
</script>
