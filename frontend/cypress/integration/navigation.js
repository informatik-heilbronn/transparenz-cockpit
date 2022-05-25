context('Login', () => {
  it('can navigate around the website', () => {
    cy.visit('/')

    cy.contains('Startseite').click()
    cy.location('pathname').should('include', 'startseite')

    cy.contains('Auswertung').click()
    cy.location('pathname').should('include', 'auswertung')

    cy.contains('Neue Steckbriefe').click()
    cy.location('pathname').should('include', 'steckbrief')

    cy.get('a[href*="account"]').click()
    cy.location('pathname').should('include', 'account')
  })
})