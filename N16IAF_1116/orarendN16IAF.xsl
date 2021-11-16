<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
    <h2>Zán Martin órarend - 2021/22. I. félév.</h2>
    <table border="1">
      <xsl:for-each select="class/student/hetfo">
        <tr>
          <td><xsl:value-of select="@id"/></td>
          <td><xsl:value-of select="vezeteknev"/></td>
          <td><xsl:value-of select="keresztnev"/></td>
          <td><xsl:value-of select="becenev"/></td>
          <td><xsl:value-of select="kor"/></td>
        </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>

</xsl:stylesheet>