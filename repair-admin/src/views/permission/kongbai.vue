<template>
    
</template>

<script>
    export default {
        name: "kongbai",
        // create(){
        //   console.log(20)
        //   this.redirect()
        // },
        data() {
          this.$router.replace({
            path: '/user/role'
          })
          return{

          }
        },
        method:{
          redirect(){
            this.$router.replace({
              path: 'role'
            })
          }
        }
    }
</script>

<style scoped>

</style>
