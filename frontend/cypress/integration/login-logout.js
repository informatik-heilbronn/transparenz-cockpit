context('Login', () => {
  it('can navigate around the website', () => {
    cy.visit('/startseite')
    cy.contains('Zugriff verweigert').should('exist')

    cy.contains('Sign in').click()

    // falsche daten
    cy.get('form').contains('Username').type('falscher name')
    cy.get('form').contains('Password').type('falsches passwort')
    cy.get('form').contains('Sign in').click()
    cy.location('pathname').should('include', 'error')
    cy.contains('Versuchen sie es erneut.').should('exist').click()

    // richtige daten
    cy.get('form').contains('Username').type('admin')
    cy.get('form').contains('Password').type('admin')
    cy.get('form').contains('Sign in').click()

    cy.location('pathname').should('include', 'startseite')

    cy.contains('Sign out').click()
    cy.location('pathname').should('include', 'startseite')
    cy.contains('Zugriff verweigert').should('exist')
  })
})