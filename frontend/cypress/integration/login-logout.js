context('Login', () => {
  it('can navigate around the website', () => {
    cy.visit('/startseite')
    cy.contains('Zugriff verweigert').should('exist')

    cy.contains('Anmelden').click()

    // falsche daten
    cy.get('input[name="username"]').type("falscher name")
    cy.get('input[name="password"]').type("falsches passwort")
    cy.get('form').contains('Anmelden').click()
    cy.location('pathname').should('include', 'error')
    cy.contains('Versuchen sie es erneut.').should('exist').click()

    // richtige daten

    cy.get('input[name="username"]').type("admin")
    cy.get('input[name="password"]').type("admin")
    cy.get('form').contains('Anmelden').click()

    cy.location('pathname').should('include', 'startseite')

    cy.contains('Abmelden').click()
    cy.contains("Ja").click()
    cy.location('pathname').should('include', 'startseite')
    cy.contains('Zugriff verweigert').should('exist')
  })
})