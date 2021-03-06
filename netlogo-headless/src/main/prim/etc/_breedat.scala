// (C) Uri Wilensky. https://github.com/NetLogo/NetLogo

package org.nlogo.prim.etc

import org.nlogo.agent.{ AgentSet, AgentSetBuilder }
import org.nlogo.api.AgentException
import org.nlogo.core.AgentKind
import org.nlogo.nvm.{ Context, Reporter }

class _breedat(breedName: String) extends Reporter {

  override def toString =
    super.toString + ":" + breedName

  override def report(context: Context): AgentSet = {
    val dx = argEvalDoubleValue(context, 0)
    val dy = argEvalDoubleValue(context, 1)
    val patch =
      try context.agent.getPatchAtOffsets(dx, dy)
      catch { case _: AgentException =>
        return world.noTurtles }
    if (patch == null)
      world.noTurtles
    else {
      val builder = new AgentSetBuilder(AgentKind.Turtle, patch.turtleCount)
      val breed = world.getBreed(breedName)
      val iter = patch.turtlesHere.iterator
      while(iter.hasNext) {
        val turtle = iter.next()
        if (turtle != null && (turtle.getBreed eq breed))
          builder.add(turtle)
      }
      builder.build()
    }
  }

}
