import NextAuth from 'next-auth'
import Providers from 'next-auth/providers'

const options = {
  providers: [
    Providers.Credentials({
      // The name to display on the sign in form (e.g. 'Sign in with...')
      name: 'UserName',
      // The credentials property is used to generate a suitable form on the sign in page.
      credentials: {
        username: { label: 'Username', type: 'text' },
        password: { label: 'Password', type: 'password' }
      },
      async authorize (credentials) {
        // Authentication Logic: local function, external API call, etc
        const user = { name: credentials.username, password: credentials.password }
        // checking the credititials

        if (user.name !== 'admin' || user.password !== 'admin') {
          return Promise.reject(new Error('Falsche E-Mail oder Passwort.'))
        } else {
          return user
        }
      }
    })
  ],

  pages: {
    signIn: '/login',
    error: '/error'
  },

  callbacks: {
    redirect: async (url, baseUrl) => {
      if (url === '/signin') {
        return Promise.resolve('/startseite')
      }
      return Promise.resolve('/startseite')
    }
  },

  debug: false

}

export default (req, res) => NextAuth(req, res, options)
